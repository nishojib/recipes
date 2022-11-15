import { CircularProgress, Container, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useQuery } from '@tanstack/react-query';

import { MyAppBar, RecipeForm } from '../../../components';
import { RecipeFieldValues, Recipe } from '../../../models';

export default function EditRecipe() {
  const router = useRouter();
  const recipeId = router.query.id;

  if (recipeId) {
    const { isLoading, data: recipes } = useQuery(['recipes'], () =>
      fetch(`http://localhost:4567/recipes/${recipeId}`).then((res) =>
        res.json()
      )
    );

    if (isLoading && !recipes) return <CircularProgress />;

    const { id, servings, healthScore, readyInMinutes, ...rest }: Recipe =
      recipes?.data[0];

    const values: RecipeFieldValues = {
      ...rest,
      servings: servings.toString(),
      healthScore: healthScore.toString(),
      readyInMinutes: readyInMinutes.toString(),
    };

    return (
      <>
        <MyAppBar />
        <Container sx={{ paddingTop: 4 }} maxWidth="sm">
          <Typography gutterBottom variant="h4">
            Edit Recipe
          </Typography>
          <RecipeForm
            defaultValues={values}
            onSuccess={(data) => console.log(data)}
            buttonText="Edit"
          />
        </Container>
      </>
    );
  } else {
    return <CircularProgress />;
  }
}
