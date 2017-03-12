import os
import requests
import urllib
import socket

s = socket.socket()
s.bind((socket.gethostname(),42069))
s.listen(15)

while True:
	print("Waiting for connection.")

	c, addr = s.accept()

	print("Connected with client "+c)
	print("Querying server for users.")

	urllib.quote('api@sendbird.com', safe='')
	api_headers = {'Api-Token': '70af27ed53bf91dcfd20e6df4b0e74860b9f26e5'}
	res = requests.get('http://api.sendbird.com/v3/users',headers=api_headers)

	print("Received users: "+res.json())

	c.send(res.json)

	c.close()