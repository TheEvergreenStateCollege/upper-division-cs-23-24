def key_mappings(sequence, dictionary):
    # Define the mappings
    keypad_mapping = {
                    '2': 'abc', '3': 'def',
                    '4': 'ghi', '5': 'jkl', '6': 'mno',
                    '7': 'pqrs', '8': 'tuv', '9': 'wxyz'
                    }

    # Function to generate all possible combinations of letters for a given sequence
    def phone_sequence(seq_index, current_word):
        if seq_index == len(sequence):
            # Check if the current word is in the dictionary
            if current_word in dictionary:
                result.append(current_word)
            return

        digit = sequence[seq_index]
        letters = keypad_mapping[digit]

        for letter in letters:
            phone_sequence(seq_index + 1, current_word + letter)

    result = []
    phone_sequence(0, '')

    return result

_example_sequence = "968"
dictionary = {"foo", "bar", "now", "you"}
result = key_mappings(_example_sequence, dictionary)
print(result)
