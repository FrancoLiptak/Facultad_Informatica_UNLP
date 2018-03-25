def en_palabras time = Time.now
    case time.min
        when 0..10
            p "Son las #{time.hour} en punto"
        when 11..20
            p "Son las #{time.hour} y cuarto"
        when 21..34
            p "Son las #{time.hour} y media"
        when 35..44
            p "Son las #{time.hour + 1} menos vinticinco"
        when 45..55 
            p "Son las #{time.hour + 1} menos cuarto"
        else
            p "Casi las #{time.hour + 1}"
    end
end

en_palabras(Time.now)
