async function hitIt() {
	console.log("UR MUM");
	const name = document.getElementById("input-name").value;
	const password = document.getElementById("input-password").value;

	alert("Name: " + name + "\nPassword: " + password);

	// Handle the fetch asynchronously
		try {
		const response = await fetch("http://accordingtoallknownlawsofaviationthereisnowayabee.arcology.builders:5000/login", {
			method: "post",
			body: JSON.stringify({ name, password }), // sending as JSON
			headers: {
				'Content-Type': 'application/json'
			}
		});

		 const data = await response.json();
		console.log(data);
	} catch (error) {
		console.error('Error:', error);
	}
}

document.getElementById("button-submit").addEventListener("click", async function(event) {
event.preventDefault();
console.log("Form submitted.");

await hitIt();
});

