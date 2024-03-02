const fetchPet = async ({queryKey}) => {
    const id = queryKey[1];

    const apiRes = await fetch(`https://pets-vs.dev-apis.com/pets?id=${id}`);

    if (!apiRes.ok) {
        throw new Error(`details/${id} fetch no work :(`);
    }

    return apiRes.json();
};

export default fetchPet;