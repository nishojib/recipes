import { Container, Typography } from '@mui/material';

import { MyAppBar } from '../components';

export default function Home() {
  return (
    <>
      <MyAppBar />
      <Container sx={{ paddingTop: 4 }}>
        <Typography>Welcome to our Project</Typography>
      </Container>
    </>
  );
}
