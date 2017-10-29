def fibonacci(n)
    a = b = 1
    result = []

    loop do
        break if result.size >= n
        result << a
        a, b = b, a + b
    end

    result
end