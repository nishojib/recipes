import {
  CheckboxElement,
  FormContainer,
  SubmitHandler,
  TextFieldElement,
} from 'react-hook-form-mui';

import { Box, Button } from '@mui/material';

import { RecipeFieldValues } from '../models';
import { FC } from 'react';

interface RecipeFormProps {
  defaultValues: RecipeFieldValues;
  onSuccess: SubmitHandler<RecipeFieldValues>;
  buttonText: string;
}

export const RecipeForm: FC<RecipeFormProps> = ({
  defaultValues,
  onSuccess,
  buttonText,
}) => {
  return (
    <FormContainer<RecipeFieldValues>
      defaultValues={defaultValues}
      onSuccess={onSuccess}
    >
      <TextFieldElement
        sx={{ marginBottom: 2 }}
        name="title"
        fullWidth
        label="Title"
      />
      <TextFieldElement
        sx={{ marginBottom: 2 }}
        name="image"
        fullWidth
        label="Image"
      />
      <Box display="flex" sx={{ marginBottom: 2 }}>
        <TextFieldElement
          sx={{ marginRight: 2 }}
          type="number"
          name="servings"
          fullWidth
          label="Servings"
        />
        <TextFieldElement
          sx={{ marginRight: 2 }}
          type="number"
          name="healthScore"
          fullWidth
          label="Health Score"
        />
        <TextFieldElement
          type="number"
          name="readyInMinutes"
          fullWidth
          label="Ready In Minutes"
        />
      </Box>
      <Box mb={2}>
        <CheckboxElement name="cheap" label="Cheap" />
        <CheckboxElement name="glutenFree" label="Gluten Free" />
        <CheckboxElement name="dairyFree" label="Dairy Free" />
      </Box>
      <TextFieldElement
        sx={{ marginBottom: 2 }}
        name="instructions"
        fullWidth
        maxRows={4}
        label="Instructions"
      />
      <TextFieldElement
        sx={{ marginBottom: 2 }}
        name="summary"
        fullWidth
        maxRows={4}
        label="Summary"
      />
      <Button variant="contained" type="submit">
        {buttonText} Recipe
      </Button>
    </FormContainer>
  );
};
