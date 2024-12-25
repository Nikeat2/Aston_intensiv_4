package com.example.astonhomework4

object MockUsers {
    val mockUsers = mutableListOf(
        Users(
            id = 1,
            image = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Иван",
            surname = "Иванов",
            phoneNumber = "+79123456789"
        ), Users(
            id = 2,
            image = "https://randomuser.me/api/portraits/women/1.jpg",
            name = "Мария",
            surname = "Петрова",
            phoneNumber = "+79129876543"
        ), Users(
            id = 3,
            image = "https://randomuser.me/api/portraits/men/2.jpg",
            name = "Сергей",
            surname = "Сидоров",
            phoneNumber = "+79051234567"
        ), Users(
            id = 4,
            image = "https://randomuser.me/api/portraits/women/2.jpg",
            name = "Анна",
            surname = "Кузнецова",
            phoneNumber = "+79034567890"
        )
    )
}