
Julian and Gavin worked on this

### Gavin's notes:

I deleted the outdated files that were here previously and added the current loader files and copied network.py from the source again. I fixed the python2 outdated syntax in network.py as well. I wasn't able to try running the network because Paul didn't finish the loader until the end of class.

---

After class:

### Fixes:

In network.py, I updated the print statement properly

```Python
            if test_data:
                print(f"Epoch {j}: {self.evaluate(test_data)} / {n_test}")
            else:
                print(f"Epoch {j} complete")
```

Added list() to wrap the test and training data

```Python
training_data = list(original_training_data)

# shape is (10000, 784, 1)
(test_images, width, height) = load_mnist.load_all_test_images()
# shape is (10000, 10)
test_labels = load_mnist.load_all_test_labels()
# shape is (10000, ((784, 1), 10))
# 10000 2-tuples, of (x,y) image,label training pair
test_data = list(zip(test_images, test_labels))
```

I also made the labels not get turned in to size 10 vectors, because network.py actually just wants the labels as ints. The `one_hot_vector_from_label()` function is unneccesary.

```Python
#return np.array([one_hot_vector_from_label(l) for l in data[8:]])
    return np.array([l for l in data[8:]])
```

I'm not sure what to make of this error, but the code seems to be working so whatever

```RuntimeWarning: overflow encountered in exp
  return 1.0/(1.0+np.exp(-z))
```

Running this the first time, it was very slow.

eta = 0.001
```
Epoch 0: 1440 / 10000
Epoch 1: 1583 / 10000
Epoch 2: 1633 / 10000
Epoch 3: 1703 / 10000
Epoch 4: 1772 / 10000
Epoch 5: 1819 / 10000
Epoch 6: 1891 / 10000
Epoch 7: 1918 / 10000
Epoch 8: 1971 / 10000
Epoch 9: 2018 / 10000
Epoch 10: 2041 / 10000
```

Trying again with different learning rates...

eta = 0.1
```
Epoch 0: 3172 / 10000
Epoch 1: 3614 / 10000
Epoch 2: 3868 / 10000
Epoch 3: 4833 / 10000
Epoch 4: 5217 / 10000
Epoch 5: 5225 / 10000
Epoch 6: 5352 / 10000
Epoch 7: 5471 / 10000
Epoch 8: 5850 / 10000
Epoch 9: 5910 / 10000
Epoch 10: 6093 / 10000
```

eta = 1.0
```
Epoch 0: 3163 / 10000
Epoch 1: 3994 / 10000
Epoch 2: 3760 / 10000
Epoch 3: 4715 / 10000
Epoch 4: 5623 / 10000
Epoch 5: 5488 / 10000
Epoch 6: 4604 / 10000
Epoch 7: 4669 / 10000
Epoch 8: 4916 / 10000
Epoch 9: 5233 / 10000
Epoch 10: 4886 / 10000
```

eta = 5.0
```
Epoch 0: 1214 / 10000
Epoch 1: 1229 / 10000
Epoch 2: 648 / 10000
Epoch 3: 648 / 10000
Epoch 4: 649 / 10000
```

I'm going to try again with 0.05 and let it run the fill 30 epochs. This learning rate looks the most promising.

Also, I added a show_progress param to SGD which determines whether it tests the algorithm every epoch, or only at the end. (also added the test at the end)
```Python
            if show_progress and test_data:
                print(f"Epoch {j}: {self.evaluate(test_data)} / {n_test}")
            else:
                print(f"Epoch {j} complete")
        print(f"Training complete. Correct test results: {self.evaluate(test_data)} out of {n_test}")
```

0.05
test results: 6378 out of 10000

0.01
test results: 3568 out of 10000

0.1
test results: 6448 out of 10000

Hmm. I'm not sure if it needs more epochs, or a change in size, but none of these results are close to the promised 96% correctness.

This is as much as I will try for now.
