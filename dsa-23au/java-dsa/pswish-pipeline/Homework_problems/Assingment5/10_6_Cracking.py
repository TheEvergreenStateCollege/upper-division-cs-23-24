# 10.6 Cracking the code
# Sort Big File: External Sort
# Imagine you avea 20Gb file with one string per line. Explain how you would sort the file.
# Could use pickle, divide up into chunk sizes of available memory

def external_sort(input_file, output_file, chunk_size):
    # Read the file in chunks and sort each chunk
    try:
        chunks = []
        with open(input_file, 'r') as infile:
            while True:
                chunk = [next(infile, None) for _ in range(chunk_size)]
                if not any(chunk):
                    break  # Reached the end of the file
                chunk.sort()  # In-memory Tim sorting
                chunks.append(chunk)

        # Merge sorted chunks
        with open(output_file, 'w') as outfile:
            while chunks:
                smallest_chunk = min(chunks, key=lambda x: x[0])
                smallest_line = smallest_chunk.pop(0)
                outfile.write(smallest_line)
                if not smallest_chunk:
                    chunks.remove(smallest_chunk)
    except Exception as e:
        print(e)

# Example:
external_sort('large_file.txt', 'sorted_file.txt', 10000)
