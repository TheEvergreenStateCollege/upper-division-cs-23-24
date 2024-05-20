with open("./inverting-the-pyramid.txt") as f:
    lines = f.readlines()

for line in lines:
    line = bytearray(line, 'utf-8')
    for j in range(len(line)):
        #print(line[j])
        if ((line[j]) == 0x0c):
            #print("invalid character")
            line[j] = 0x20
    print(line.decode())