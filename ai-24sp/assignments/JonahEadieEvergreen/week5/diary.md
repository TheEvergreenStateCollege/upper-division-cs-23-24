## Output 1

```
torch version: 2.2.2
tiktoken version: 0.6.0
Total number of characters: 5652822
Capital
A Critique of Political Economy
Volume I
Book One: The Process of Production of Capital
Fir
```

## Output 2

```
['Capital', 'A', 'Critique', 'of', 'Political', 'Economy', 'Volume', 'I', 'Book', 'One:', 'The', 'Process', 'of', 'Production', 'of', 'Capital', 'First', 'published:', 'in', 'German', 'in', '1867', ',', 'English', 'edition', 'first', 'published', 'in', '1887;', 'Source:']
```

## Output 3

```
('Capital', 3512)
('Capital-value', 3513)
('Capitalisation', 3514)
('Capitalising', 3515)
('Capitalist', 3516)
('Capitalistic', 3517)
('Capitalists', 3518)
('Capitals', 3519)
('Capps', 3520)
('Captain', 3521)
('Car', 3522)
('Carbon', 3523)
('Card', 3524)
('Cardiganshire', 3525)
('Cardwell', 3526)
('Carey', 3527)
('Carli', 3528)
('Carlile', 3529)
('Carlisle', 3530)
('Carlyle', 3531)
('Carmarthenshire', 3532)
('Carolinian', 3533)
('Carpet', 3534)
('Carthage', 3535)
('Case', 3536)
('Cash', 3537)
('Cashier', 3538)
('Cast', 3539)
('Castes', 3540)
('Castle', 3541)
('Castlereagh', 3542)
('Casualties', 3543)
('Casuists', 3544)
('Catalonians', 3545)
('Categories', 3546)
('Category', 3547)
('Catherine', 3548)
('Catholic', 3549)
('Catholicism', 3550)
('Catholics', 3551)
('Cato', 3552)
('Cattle', 3553)
('Caus', 3554)
('Causes', 3555)
('Cayley', 3556)
('Cazenove', 3557)
('Ce', 3558)
('Celebes', 3559)
('Celtic', 3560)
('Celts', 3561)
('Censor', 3562)
('Census', 3563)
('Central', 3564)
('Centrale', 3565)
('Centralisation', 3566)
('Centuries', 3567)
('Century', 3568)
('Cependant', 3569)
('Cereal', 3570)
('Certain', 3571)
```

## Output 4

### Input

```
the wealth of societies in which the capitalist mode of production prevails appears as an immense collection of commodities
```

### Output

```
[24947, 26601, 19342, 23620, 16267, 26717, 24947, 10011, 18494, 19342, 21057, 20912, 8705, 8875, 8535, 16130, 10658, 19342, 10755]
```

## Output 5

```
the wealth of societies in which the capitalist mode of production prevails appears as an immense collection of commodities
```

## Output 6

```
Traceback (most recent call last):
  File "/Users/jonaheadie/Development/school/ai-final/marx/upper-division-cs/ai-24sp/assignments/JonahEadieEvergreen/week5/process.py", line 48, in <module>
    ids = tokenizer.encode("FLCL was revolutionary, breaking many conventions of the medium of Japanese animation")
          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/Users/jonaheadie/Development/school/ai-final/marx/upper-division-cs/ai-24sp/assignments/JonahEadieEvergreen/week5/tokenizer.py", line 15, in encode
    ids = [self.str_to_int[s] for s in preprocessed]
           ~~~~~~~~~~~~~~~^^^
KeyError: 'FLCL'
```

## Output 8

```
('zurechtgestmpert', 27087)
('{', 27088)
('}', 27089)
('<|endoftext\\>', 27090)
('<|unk|>', 27091)
```
