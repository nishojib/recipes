import {
  Container,
  Button,
  CircularProgress,
  Typography,
  Chip,
  Box,
  Stack,
  List,
  ListItem,
  ListItemText,
} from '@mui/material';
import Link from 'next/link';
import Image from 'next/image';
import { useRouter } from 'next/router';
import { useQuery, useMutation } from '@tanstack/react-query';

import { MyAppBar } from '../../../components';
import { Recipe, Ingredient } from '../../../models';

export default function RecipeDetail() {
  const router = useRouter();
  const recipeId = router.query.id;

  if (recipeId) {
    const { isLoading, data: recipes } = useQuery(['recipes'], () =>
      fetch(`http://localhost:4567/recipes/${recipeId}`).then((res) =>
        res.json()
      )
    );

    const { isLoading: ingredientsLoading, data: ingredients } = useQuery(
      ['ingredients'],
      () =>
        fetch(`http://localhost:4567/recipes/${recipeId}/ingredients`).then(
          (res) => res.json()
        )
    );

    const { mutate: deleteRecipe } = useMutation(
      (id: string) =>
        fetch(`http://localhost:4567/recipes/${id}`, { method: 'DELETE' }),
      {
        async onSuccess(data) {
          await router.push('/recipes');
        },
      }
    );

    const handleDeleteRecipe = () => {
      if (window.confirm('Are you sure?')) {
        if (typeof recipeId === 'string') {
          deleteRecipe(recipeId);
        }
      }
    };

    let content;
    if (isLoading && !recipes) {
      content = <CircularProgress />;
    } else {
      const recipe: Recipe = recipes.data[0];
      content = (
        <>
          <Image
            src={recipe.image}
            height="500"
            width="700"
            alt={recipe.title}
          />
          <Typography gutterBottom variant="h2">
            {recipe.title}
          </Typography>
          <Stack direction="row" spacing={1} sx={{ marginBottom: 2 }}>
            {recipe.cheap && <Chip color="success" label="Cheap" />}
            {recipe.glutenFree && <Chip color="primary" label="Gluten Free" />}
            {recipe.dairyFree && <Chip color="warning" label="Dairy Free" />}
          </Stack>
          <Typography
            dangerouslySetInnerHTML={{ __html: recipe.summary }}
          ></Typography>
          <Box mt={2}>
            {ingredientsLoading && !ingredients ? (
              <CircularProgress />
            ) : (
              <>
                <Typography sx={{ fontWeight: 'bold' }} variant="h5">
                  Ingredients
                </Typography>
                <List dense>
                  {ingredients.data.map((ingredient: Ingredient) => {
                    return (
                      <ListItem key={ingredient.id}>
                        <ListItemText primary={ingredient.original} />
                      </ListItem>
                    );
                  })}
                </List>
              </>
            )}
          </Box>
          <Typography sx={{ fontWeight: 'bold' }} variant="h5">
            Instructions
          </Typography>
          <Typography
            dangerouslySetInnerHTML={{ __html: recipe.instructions }}
          ></Typography>
        </>
      );
    }

    return (
      <>
        <MyAppBar />
        <Container sx={{ paddingTop: 4 }}>
          <Stack justifyContent="flex-end" direction="row" spacing={1}>
            <Button
              variant="contained"
              color="warning"
              component={Link}
              href={`/recipes/${recipeId}/edit`}
            >
              Edit
            </Button>
            <Button
              variant="contained"
              color="error"
              onClick={handleDeleteRecipe}
            >
              Delete
            </Button>
          </Stack>
          <Box mt={4}>{content}</Box>
        </Container>
      </>
    );
  } else {
    return <CircularProgress />;
  }
}
