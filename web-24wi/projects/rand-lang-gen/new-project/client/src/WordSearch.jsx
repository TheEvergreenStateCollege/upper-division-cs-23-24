import { useState } from 'react';

export default function WordSearch() {
    const [word, setWord] = useState();
    const [def, setDefine] = useState();
    const [isVisible, setIsVisible] = useState(false);

    const fetchWordAndDefinition = async () => {
        try {
            const wordResponse = await fetch(`https://random-word-api.herokuapp.com/word`);
            const wordData = await wordResponse.json();
            if (wordData && wordData.length > 0) {
                const word = wordData[0];
                setWord(word);
                console.log(word);

                const definitionResponse = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`);
                const definitionData = await definitionResponse.json();
                if (definitionData && definitionData.length > 0) {
                    setDefine(definitionData[0].meanings);
                    console.log(definitionData[0].meanings);
                } else {
                    console.error("Error: No definition found in response.");
                }
            } else {
                console.error("Error: No word found in response.");
            }
        } catch (error) {
            console.error("Error fetching word or definition:", error);
        }
    };

    const handleButtonClick = () => {
        setIsVisible(true);
        fetchWordAndDefinition();
    };

    return (
        <div>
            <button onClick={handleButtonClick}>
                {isVisible ? 'Generate' : 'Show'} New Word
            </button>
            {isVisible && (
                <div className="font-mono ml-20 py-20 grid grid-rows-2 gap-2">
                    <div>
                        <p className='text-3xl'>a word-generator that is sometimes wrong</p>
                    </div>
                    <div>
                        <p className='text-9xl'>{word}</p>
                        {def && def.map((meaning, index) => (
                            <div key={index}>
                                <p className="text-3xl ml-20 italic">{meaning.partOfSpeech}</p>
                                <p className="text-3xl ml-20">{meaning.definitions[0].definition}</p>
                            </div>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}