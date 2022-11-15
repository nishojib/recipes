import {
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
} from '@mui/material';
import { FC } from 'react';
import Link from 'next/link';

interface RecipeCardProps {
  id: number;
  image: string;
  title: string;
}

export const RecipeCard: FC<RecipeCardProps> = ({ id, image, title }) => {
  return (
    <Card>
      <CardActionArea component={Link} href={`/recipes/${id}`}>
        <CardMedia
          sx={{
            height: 250,
            width: '100%',
            backgroundSize: 'cover',
          }}
          component="img"
          image={image}
          alt={title}
        />
        <CardContent>
          <Typography variant="h5" component="h2">
            {title}
          </Typography>
        </CardContent>
      </CardActionArea>
    </Card>
  );
};
