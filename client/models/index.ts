export interface StandardResponse {
  status: 'SUCCESS' | 'ERROR';
  data: Recipe[] | Ingredient[];
}

export interface Recipe {
  id: number;
  title: string;
  image: string;
  servings: number;
  healthScore: number;
  cheap: boolean;
  glutenFree: boolean;
  dairyFree: boolean;
  readyInMinutes: number;
  instructions: string;
  summary: string;
}

export interface RecipeFieldValues {
  title: string;
  image: string;
  servings: string;
  healthScore: string;
  cheap: boolean;
  glutenFree: boolean;
  dairyFree: boolean;
  readyInMinutes: string;
  instructions: string;
  summary: string;
}

export interface Ingredient {
  id: number;
  name: string;
  amount: number;
  unit: string;
  original: string;
}
