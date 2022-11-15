import { Container, Typography } from '@mui/material';

import { MyAppBar, RecipeForm } from '../../components';
import { RecipeFieldValues } from '../../models';

const values: RecipeFieldValues = {
  title: '',
  image: '',
  servings: '',
  cheap: false,
  glutenFree: false,
  dairyFree: false,
  healthScore: '',
  readyInMinutes: '',
  instructions: '',
  summary: '',
};

export default function CreateRecipe() {
  return (
    <>
      <MyAppBar />
      <Container sx={{ paddingTop: 4 }} maxWidth="sm">
        <Typography gutterBottom variant="h4">
          Create a new recipe
        </Typography>
        <RecipeForm
          defaultValues={values}
          onSuccess={(data) => console.log(data)}
          buttonText="Create"
        />
      </Container>
    </>
  );
}
