import { useState, useEffect } from 'react';

export default function WordSearch() {
    const [word, setWord] = useState();
    const [def, setDefine] = useState();
    useEffect(() => {
        fetch(`https://random-word-api.herokuapp.com/word`)
        .then((response) => response.json())
        .then((data) => {
            if(data && data.length > 0) {
            setWord(data[0])
            console.log(data[0])
            }else {
                console.error("Error: No word found in response.");
            }
        })
        .catch((error) => {
            console.error("Error fetching word:", error);
        });
    }, []);
    useEffect(() => {
        if (word) {
        fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`)
        .then((response) => response.json())
        .then((data) => {
            if (data && data.length > 0) {
            setDefine(data[0].meanings)
            console.log(data[0].meanings)
            } else {
                console.error("Error: No definition found in response.");
            }
        })
        .catch((error) => {
            console.error("Error fetching definition:", error);
        });
        }
    }, [word]);

return (
    <div>
    <div className="font-mono ml-20  py-20 grid grid-rows-2 gap-2">
    <div>
    <p className='text-3xl'>a word-generator that is sometimes wrong</p>
    </div>
    <div>
        <p className='text-9xl'>
            {word}
        </p>
                {def && def.map((meaning, index) => {
                    return (
                        // eslint-disable-next-line react/jsx-key
                        <div key = {index}>
                            <p className="text-3xl ml-20 italic">{meaning.partOfSpeech}</p>
                            <p className="text-3xl ml-20">{meaning.definitions[0].definition}</p>
                        </div>
                    )
                })}
    </div>
    </div>
    </div>
)
}