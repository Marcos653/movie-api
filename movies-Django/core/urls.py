from django.conf.urls import include
from django.urls import path
from .views import *
from rest_framework.authtoken import views
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'movie', MovieViewSet)


urlpatterns = [
    path('', include(router.urls)),
]