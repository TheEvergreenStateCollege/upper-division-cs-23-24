import redis

r = redis.Redis(
  host='redis-12380.c325.us-east-1-4.ec2.cloud.redislabs.com',
  port=12380,
  password='lSghCA2sB3fvwM27IvgBkr2QzFEbLFkH')

r.set('foo','bar')

result = r.get('foo')
print(result)