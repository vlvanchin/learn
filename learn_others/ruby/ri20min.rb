class MegaGreeter
	attr_accessor :names

	def initialize(names = "World")
		@names = names
	end

	def say_hi
		if @names.nil?
			puts "..."
		elsif @names.respond_to?("each")
			@names.each do |name|
				puts "hello #{name}"
			end
		else
			puts "hello #{@names}"
		end
	end

	def say_bye
		if @names.nil?
			puts "..."
		elsif @names.respond_to?("join")
			puts "Goodbye #{@names.join(", ")}. See you all soon"
		else
			puts "Goodbye #{@names}. See you soon"
		end
	end
end

if __FILE__ == $0
	mg = MegaGreeter.new
	mg.say_hi
	mg.say_bye

	mg.names = "vanchin"
	mg.say_hi
	mg.say_bye

	mg.names = ["Bagvath", "Subbash", "Susi" , "vanchi"]
	mg.say_hi
	mg.say_bye

end