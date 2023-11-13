from django.db import models

class DriverData(models.Model):
    key = models.CharField(max_length=255, unique=True)
    value = models.JSONField()
