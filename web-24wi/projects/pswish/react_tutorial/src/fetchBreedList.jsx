const fetchBreedList = async ({ queryKey }) => {
  const animal = queryKey[1];

  if (!animal) return [];

  const apiRes = await fetch(
    `http://pets-v2.dev-apis.com/breeds?animal=${animal}`
  );

  if (!apiRes.ok)
    throw new Error(`details/${animal} not found in apiRes, fetch not ok`);

  return apiRes.json();
};

export default fetchBreedList;
