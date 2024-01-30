
# Importing the PIL library
from PIL import Image
from PIL import ImageDraw
 
# Open an Image
img = Image.open('robolove.png')
 
# Call draw Method to add 2D graphics in an image
I1 = ImageDraw.Draw(img)
 
# Add Text to an image
I1.text((50, 0), "Nice Butt!", fill=(255, 255, 255), align ="left")
 
# Display edited image
img.show()
 
# Save the edited image
img.save("robolove-stamped.png")

