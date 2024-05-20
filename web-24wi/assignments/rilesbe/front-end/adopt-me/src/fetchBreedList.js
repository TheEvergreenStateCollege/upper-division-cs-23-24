const fetchBreedList = async ({queryKey}) => {
    const animal = queryKey[1];

    if (!animal) return [];
    const apiRes = await fetch(`https://pets-v2.dev-apis.com/breeds?animal=${animal}`);

    if (!apiRes.ok) {
        throw new Error(`breeds/${animal} fetch no work :(`);
    }

    return apiRes.json();
};

export default fetchBreedList;