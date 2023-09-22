# Chapter 3: Greed---Satisficing---Optimality

## Spaced Repetition

Chadwick picks glumly at his calzone.

"What's wrong now, stuck in a filter bubble of doom?" Idris asks.

His friend chuckles weakly. "Close. I have that big algorithms exam coming up in six weeks,
and I'm having trouble remembering everything. I think I've reached the biological limits of
my memory."

Idris snorts. "You know, neuroscience hasn't discovered such a limit. Neurogenesis is just 
a theory, but there is some evidence that adults keep generating new brain cells even after
age 25. That limit is just a scientific urban legend."

"You mean I can just remember things infinitely if I try hard enough?"

"Not by brute force. But have you heard of this technique called spaced repetition?"

"Is that what NASA calls its program of sending space probes out to die in the utter vacuum of
the cosmos?"

"Oh look who's got jokes now. No dumbass,
spaced repetition is a theory of learning that says if have a structured schedule of learning concepts,
you can repeat them again at longer and longer intervals. Every day in the schedule, you can
learn something new or repeat a previous concept. At first you repeat it the next day, then in
three days, then seven, and so on.. basically forever if you can keep track of it."

Chadwick muses this.
"Is the schedule different for everyone, or the same?"

"That's a good question, I don't know. That would be a great dissertation for an education
major. Say weren't you seeing that hottie who was getting a Master's in Teaching..."

"Let's stay on topic," Chadwick waved off his friend's attempt to get details. "Spaced repetition.
So if I have six weeks, how many days of information can I learn?"

"Enough handwaving, let's get serious," Idris knocks all the stray potato chip wrappers,
energy drink bottles, and clutter off the desk and grabs an empty sheet of paper. He clicks
open his pen. "Let's give these variables names. First, let's label the Day 1 material."

$1, ..., 1, ..., ..., 1, ..., ..., ..., ..., 1, ..., $

Let's call a day's worth of learning material a Bok.

Why not a Book?

"What are you, chicken?"

"Have you heard of spaced repetitino?"

Let's say the schedule for each day is given by the term $x_{i,j}$, where $i$ is the 
material for Day $i$ and $j$ is the iteration. So the first time you learn the material
Day 1 is

$x_{1,1}$

and the allowed gap until the next iteration from $x_{i,j}$ is between $a x_{i,j}$ and 
$(a+1) x_{i,j}. So the second time you learn the material Day 1 is 

$a x_{1,1} \le x_{1,2} \le (a+1) x_{1,1}$

Let's say you schedule each day's repetition $x_{i,j}$ in the earliest free slot in the 
range, but you also want to maximize the amount of material that you learn. Let's also say 
$k$ repetitions are sufficient for you to remember something for the test.

If you have $n$ days, how many days  
"Now, all the $...$ are empty days where you can learn new material."

"But have you heard of spaced repetition?"

"Isn't that when you have to say something twice for someone who isn't paying attention?
Very funny. Now let's label day 2 material."

$1, 2, 1, 2, ..., 1, 2, ..., ..., ..., 1, 2, $

So on Day 1, you learn Bok 1, on Day 2, you learn Bok 2, and so forth, pushing more and more 
new Boks for the first time until you reach Day $a$, when you have to repeat Bok 1 again  or 
you'll have to start its schedule over from scratch. This time, you got to repeat Boks 1 through $a$ 
on Days $a+1$ through $a+a$, or $2a$, and then you have room for another $a$ new Boks until you 
have to repeat Bok 1 again. So that's $m == 2a$ Boks so far, if $n$ is $4a$, and $k$ is 2.

Okay, I'm starting to see a pattern. So let's say $n$ is 6 weeks, or $6 \times 7 = 63$ days.

Then we rearrange 



2. second
3. third

