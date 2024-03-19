import React from "react";
import { Box, Skeleton } from "@mui/material";

// Making a loading page since application is over 500kb and thus slow to load

const SkeletonPage = () => {
  return (
    <Box p={2}>
      {/* Header Skeleton */}
      <Skeleton variant="rectangular" width="100%" height={50} />

      {/* Main Content Skeleton */}
      <Box mt={2}>
        {[1, 2, 3].map((item) => (
          <Box key={item} mb={2}>
            <Skeleton variant="rectangular" width="100%" height={150} />
          </Box>
        ))}
      </Box>
    </Box>
  );
};

export default SkeletonPage;
