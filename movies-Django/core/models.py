from django.db import models

# Create your models here.

class Movie(models.Model):
    title = models.CharField(max_length=255, null=False,blank=False)
    author = models.CharField(max_length=255, null=False,blank=False)
    genre = models.CharField(max_length=255, null=False,blank=False)

    def __str__(self) -> str:
        return (f'{self.title} - {self.author}')


