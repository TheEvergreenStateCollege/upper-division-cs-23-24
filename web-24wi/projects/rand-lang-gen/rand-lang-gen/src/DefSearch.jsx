import WordSearch from "./WordSearch";
async function DefSearch() {
const newWord = WordSearch[0];
console.log(WordSearch);
const response = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${newWord}`);

try {
	const result = await response.json();
	console.log(result);
	return result;
} catch (error) {
	console.error(error);
}
}
export default DefSearch;