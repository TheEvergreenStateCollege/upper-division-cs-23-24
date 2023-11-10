def Pesky(n):
  r = 0
  for i in range(1, n + 1):
    for j in range(1, i + 1):
      for k in range(j, i + j + 1):
        r += 1
  return r

print(Pesky(5))
# bigO for this function is O(n^3)