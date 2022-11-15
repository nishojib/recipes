import {
  CircularProgress,
  Container,
  Unstable_Grid2 as Grid,
  Button,
} from '@mui/material';
import { useQuery } from '@tanstack/react-query';
import Link from 'next/link';

import { MyAppBar, RecipeCard } from '../../components';
import { Recipe } from '../../models';

export default function Recipes() {
  const { isLoading, data: recipes } = useQuery(['recipes'], () =>
    fetch('http://localhost:4567/recipes').then((res) => res.json())
  );

  let content;
  if (isLoading && !recipes) {
    content = <CircularProgress />;
  } else {
    content = recipes.data.map((recipe: Recipe) => {
      return (
        <Grid xs={2} sm={4} md={4} key={recipe.id}>
          <RecipeCard
            id={recipe.id}
            title={recipe.title}
            image={recipe.image}
          />
        </Grid>
      );
    });
  }

  return (
    <>
      <MyAppBar />
      <Container sx={{ paddingTop: 4 }}>
        <Button component={Link} href="/recipes/create">
          Add Recipe
        </Button>
        <Grid
          container
          sx={{ paddingTop: 4 }}
          spacing={{ xs: 2, md: 3 }}
          columns={{ xs: 4, sm: 8, md: 12 }}
        >
          {content}
        </Grid>
      </Container>
    </>
  );
}
