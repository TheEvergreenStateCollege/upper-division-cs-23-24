const fetch = require('node-fetch');

async function init() {
	try {
		const response = await fetch("https://words.dev-apis.com/word-of-the-day");
		const data = await res.json();
		console.log(data);
	} catch (error) {
		console.error(error);
	}
}

init();

