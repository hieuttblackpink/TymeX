def find_missing_number(input_array):
    n = len(input_array) + 1  # The total number of elements including the missing one
    expected_sum = n * (n + 1) // 2  # Sum of numbers from 1 to n
    actual_sum = sum(input_array)  # Sum of given numbers
    return expected_sum - actual_sum

# Test input
input_array = [3, 7, 1, 2, 6, 4]  # Missing number is 5
missing_number = find_missing_number(input_array)

print(f"The missing number is: {missing_number}")
