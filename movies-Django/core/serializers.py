from .models import *
from rest_framework import serializers
from movie.settings import *


class MovieSerializer(serializers.ModelSerializer):

    class Meta:
        model = Movie
        fields = '__all__'