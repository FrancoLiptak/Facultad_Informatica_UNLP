def suma tope
    ((1..tope - 1).select { |num| num % 3 == 0 or num % 5 == 0 }).sum
end