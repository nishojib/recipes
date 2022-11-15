import { Adb as AdbIcon, Menu as MenuIcon } from '@mui/icons-material';
import {
  AppBar,
  Box,
  Button,
  Container,
  Toolbar,
  Typography,
} from '@mui/material';
import { useRouter } from 'next/router';

const pages = [
  { name: 'Recipes', link: '/recipes' },
  { name: 'Ingredients', link: '/ingredients' },
];

export const MyAppBar = () => {
  const router = useRouter();

  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <AdbIcon sx={{ mr: 1 }} />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            MEALS
          </Typography>

          <Box sx={{ flexGrow: 1 }}>
            {pages.map(({ name, link }) => (
              <Button
                key={name}
                onClick={() => router.push(link)}
                sx={{ my: 2, color: 'white', display: 'block' }}
              >
                {name}
              </Button>
            ))}
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};
