async function WordSearch() {
	const response = await fetch(`https://random-word-api.herokuapp.com/word`);

	try {
		const buffer = await response.json();
		console.log(buffer);
		return buffer;
	} catch (error) {
		console.error(error);
	}
};

export default WordSearch;