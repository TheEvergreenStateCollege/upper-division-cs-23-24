async function WordSearch() {
const url = `https://random-word-api.herokuapp.com/word?number=1`;

try {
	const response = await fetch(url);
	const buffer = await response.json();
	const result = buffer[0];
	console.log(result);
	return result;
} catch (error) {
	console.error(error);
}
};

export default WordSearch;