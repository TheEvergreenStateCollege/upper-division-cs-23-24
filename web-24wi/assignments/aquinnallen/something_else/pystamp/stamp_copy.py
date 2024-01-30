from flask import Flask, json, jsonify, request
from PIL import Image, ImageDraw, ImageFont
from flask_cors import CORS

api = Flask(__name__)
CORS(api)

@api.route('/check', methods = ['GET'])
def  get_check():
  return jsonify([{'message':"hello flask!"}])

"""
recieves json requests to stamp an image
format as follows:
[{
  name:String(format last,first)
  age:int
  ass:String(format name1,name2)
  image_selection:String (format x,y,filename)
"""

@api.route('/stampimage', methods = ['POST'])
def serve_image():
  image_request = json.loads(request.data)
  name = image_request['name']
  age = image_request['age']
  ass = image_request['ass']
  image_selection = image_request['image_selection']
  if age < 18:
    return "https://www.google.com/search?q=help+for+minor+attracted+persons" , 200
  url = make_image(name, age, ass, image_selection)
  res_url = {"url":url}
  response = api.response_class(
    response=json.dumps(res_url),
    status = 200,
    mimetype='application.json'
  )
  response.headers.add('Access-Control-Allow-Origin', '*')
  return response

def make_image(name, age, ass, image_selection):
  ass = ass.split(' ')
  color = (255,255,255)
  img_path = ''
  maybe_n = ''
  if 'a' == ass[0] or  'e' == ass[0] or 'i' == ass[0] or 'o' == ass[0] or 'u' == ass[0]:
    maybe_n = 'n'
  selection_info = image_selection.split(',')
  location = (int(selection_info[0]),int(selection_info[1]))
  font = ImageFont.truetype(r'/var/www/robobuttlove.com/ecard_services/fonts/impact.ttf', 20)
  message = f'Hello {name}!!!\nYou have a{maybe_n} {ass[0]}\n{ass[1]} booty! \nI can\'t get enough of it!'
  filename = (name.replace(' ','')+str(age)+".png").lower().replace(",","")
  img_path = f'/var/www/robobuttlove.com/ecard_services/images/{selection_info[2]}.png'
  img = Image.open(img_path)
  image_drawing = ImageDraw.Draw(img)
  image_drawing.text(location, message, fill = color, font = font)
  img.save(f'/var/www/robobuttlove.com/public_html/imgs/{selection_info[2]}{filename}')
  url = ('http://robobuttlove.com/imgs/'+selection_info[2]+filename)
  return url

if __name__=='__main__':
  api.run(host='0.0.0.0', port=4555)
