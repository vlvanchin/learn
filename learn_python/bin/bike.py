# lets define a class bike
class Bike:
    def __init__ (self, colour, frame_material):
        self.colour = colour
        self.frame_material = frame_material

    def brake(self):
        print("Breaking")


# lets create a couple of instances
red_bike = Bike('Red','carbon fiber')
blue_bike = Bike('Blue','steel')

# lets inspect the object
print(red_bike.colour)
print(red_bike.frame_material)
print(blue_bike.colour)
print(blue_bike.frame_material)

# lets brake
red_bike.brake()

