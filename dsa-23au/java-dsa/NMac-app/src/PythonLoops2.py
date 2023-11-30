class Solution:
    def main(self, input_string):
        lines = input_string.split("\n")
        q = int(lines[0])  # The first line contains the number of queries

        for i in range(q):
            a, b, n = map(int, lines[i + 1].split())
            current_value = a

            for j in range(n):
                addition = (2 ** j) * b
                current_value += addition
                print(current_value, end=" ")

            print()


sol = Solution()
input_string = "2\n0 2 10\n5 3 5"
sol.main(input_string)
