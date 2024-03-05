import WordSearch from "./WordSearch";
async function DefSearch() {
console.log(WordSearch);
const url = `https://api.dictionaryapi.dev/api/v2/entries/en/${WordSearch}`;

try {
	const response = await fetch(url);
	const result = await response.text();
	console.log(result);
} catch (error) {
	console.error(error);
}
}
export default DefSearch;