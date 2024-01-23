# Smarty Plants
A project to learn about programming data structures & algorithms and bioinformatics
by analyzing the mRNA of plant exhibiting novel nervous-system like behaviors.

In collaboration with
* Nancy Murray, faculty in neurobiology, The Evergreen State College
* Kelsea Jewell, faculty in biology, Centralia College

## Team Members
In The Evergreen State College computer science
* Dylana Wagorn
* Dominic Severe
* Paul Swisher
* Paul Pham

## Goals and Our Circle of Competence
This project is a proof-of-concept that has two main goals:
* provide easy-to-use, general-purpose sequencing software to the biology research community
* provide a learning platform and an inspiring motivation for undergraduate computer science students

As such, we rely on biologists for our input data and to interpret our output.

As computer scientists, we care primarily about algorithms, computation, programming, systems design,
engineering reliability and performance. As entrepreneurs, we want to make a web accessible prototype
available to our users (biologists) as soon as possible for feedback.

"I am not a genius, but I'm good in small areas and I stick to those spots." ---Tom Watson, Sr., Founder of IBM.

"Warren and I have skills that could easily be taught to other people. One skill is knowing the edge of your own competency. It’s not a competency if you don’t know the edge of it." ---Charlie Munger, Vice Chair of Berkshire Hathaway

Solving the purely computational pieces of this project would be suitable preparation for continuing on
in research or industry careers, for example, applying to graduate programs in biology or bioinformatics /
computational biology.

## Contributions
If you notice errors or have suggestions, please open a GitHub issue on this repo tagged `smarty-plants`.

## Approach
We have a short-term approach (to be completed in Winter / Spring 2024) and a long-term
approach beyond that.

### Winter / Spring 2024
We are following the program of modeling genome reconstruction as an Eulerian path problem
as described by this [2001 paper by Pevzner, Tang, Waterman](https://www.pnas.org/doi/10.1073/pnas.171285098).

Eulerian paths are related somehow to de Brujin graphs or sequences, which let us express the repeated sequences
that often occur in genomes.

The source code from this lab is no longer available.
It would be surprising if no more recent progress has been made, especially porting it to a GPU
and running parallel graph algorithms.

The outcome of our short-term approach would be that an Eulerian path approach from the paper above,
run on the given dataset, yields:
* a single completely reconstructed, consistent genome
  * with some error or confidence interval, given the qualities of the individual sequences and allowed mRNA transcription errors from the DNA
* two or more completely reconstructed, consistent genomes with
  * some configurable measure of closeness, or "error" between the distinct genomes
* takes too much time or memory to complete
  * for example, our algorithm terminates when it runs out of memory allotted to it by its operating system

We suspect that dialing down the allowed closeness between genomes is a tradeoff and would
result in more resulting genomes, while allowing the error to approach the entire length of
the genome would result in a single "noisy" genome.

Another outcome would be
* measuring the time and space complexity of our algorithm, as we dial the error up and down
* finding opportunities for parallelism
  * for example, being able to divide up the graph into pieces that can be reconstructured independently
  * lower-hanging fruit, the better

### Long-Term
Using the outcomes above, if enough parallelism is available,
we can reformulate it as a GPU problem.

## Data
The data collected is from Oxford Nanopore devices
and is stored in 61 FASTA files of about 2.8 MB each 
on this [Google Drive](https://drive.google.com/drive/folders/1y1c1cKLC-pUDmF5EN15IiBTsbF39ubeO?usp=sharing) .
Please do not distribute. Any results should mention a credit to Nancy Murray's lab.

## Notes and Resources
* [Dylana's Notes on Oxford Nanopore Minion](https://docs.google.com/document/d/1XkGZuFACHYOUfdbhtRx_EBYM4eBE4jHOC5xcQX9LlJg/edit?usp=sharing)
* Add more notes from Kelsea Jewell here
* Skiena, "The Algorithm Design Manual", 3rd edition, pp. 563-567, "Eulerian Paths / Chinese Postman", Ch.18: Graph Problems with Polynomial Complexity

## Considerations
There are biological considerations of preparing more data, whether the data was contaminated
with other species, etc.

## Subtasks

These subtasks will be created as individual GitHub issues and members can
assign them to themselves to take on the task.

0. (COMPLETED) [Loading a single FASTA file into Java](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/smartyPlants/src/main/java/com/mycompany/app/App.java)
1. Examining the FASTA data to see what quality information is available.
2. Coming up with a toy example
   - 10 fragments of 100 sequences each
   - chopping them up into 3-lengths
   - allowing two 3-lengths to overlap with at most one base pair error
3. Creating a set of unit tests and interfaces for the graph algorithm
4. Implementing the graph algorithm
   - debug and iterate on the toy example
5. Run it on increasingly more of the real dataset
  - examine whether resulting genomes vs. allowed error appears to increase in quality or decrease
  - measure time and memory performance
6. Parallelize with multiple threads (still on CPU-only in Java)
7. ... 
