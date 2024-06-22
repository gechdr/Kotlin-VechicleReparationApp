package com.example.tugasm5_6958

class MockDB {
    companion object {
        val listUser = mutableListOf<User>(
            User("Christian Morgan", "morgan", "123", "Customer", null, null, null, null),
            User("Charles Medon", "charles", "123", "Montir", "Saya sudah bekerja di bengkel ini selama 2 tahun, saya sangat berpengalaman dalam semua jenis permasalahan dalam dunia otomotif.", "2 tahun", 50000, 0),
            User("Cole Martin", "martin", "123", "Montir", "Saya sudah bekerja di bengkel ini selama 3 tahun, saya sangat berpengalaman dalam semua jenis permasalahan dalam dunia otomotif.", "3 tahun", 75000, 0),
            User("Randy Arias", "arias", "123", "Customer", null, null, null, null),
            User("Andy Tran", "andy", "123", "Montir", "Saya sudah bekerja di bengkel ini selama 4 tahun, saya sangat berpengalaman dalam semua jenis permasalahan dalam dunia otomotif.", "4 tahun", 100000, 0),
            User("Esme Watts", "esme", "123", "Montir", "Saya sudah bekerja di bengkel ini selama 1 tahun, saya kurang berpengalaman dalam semua jenis permasalahan dalam dunia otomotif.", "1 tahun", 25000, 0),
        )

        fun addUser(u: User) {
            listUser.add(u)
        }
        fun getUserSize(): Int {
            return listUser.size
        }
        fun getUserByUsername(username: String): User? {
            return listUser.find { it.username == username }
        }
        fun getUserByRole(role: String): MutableList<User> {
            return listUser.filter { it.role == role }.toMutableList()
        }

        // --------------------------------------------

        val listChat = mutableListOf<Chat>(
            Chat(1, "morgan", "charles", "Halo, saya ingin memperbaiki mobil saya."),
            Chat(2, "charles", "morgan", "Baik, apa yang terjadi dengan mobil anda?"),
            Chat(3, "morgan", "charles", "Mobil saya tidak bisa dinyalakan."),
            Chat(4, "morgan", "martin", "Halo, saya ingin memperbaiki mobil saya."),
            Chat(5, "martin", "morgan", "Baik, apa yang terjadi dengan mobil anda?"),
            Chat(6, "morgan", "martin", "Mobil saya tidak bisa dinyalakan."),

        )

        fun addChat(c: Chat) {
            listChat.add(c)
        }
        fun getLastIndexChat(): Int {
            return listChat.last().id
        }
        fun getChatBySenderNoDuplicate(sender: String): MutableList<Chat> {
            val tempList = listChat.sortedByDescending { it.id }.toMutableList()

            val tempSender = tempList.filter { it.sender == sender }.distinctBy { it.receiver }.toMutableList()
            if (tempSender.size > 0) {
                return tempSender
            }

            val tempReceiver = tempList.filter { it.receiver == sender }.distinctBy { it.sender }.toMutableList()
            if (tempReceiver.size > 0) {
                return tempReceiver
            }


            return mutableListOf()
        }
        fun getChatConvertation(sender: String, reciever: String): MutableList<Chat> {
            return listChat.filter { (it.sender == sender && it.receiver == reciever) || (it.sender == reciever && it.receiver == sender)}.toMutableList()
        }

        fun deleteChatConvertation(sender: String, reciever: String) {
            listChat.removeAll { (it.sender == sender && it.receiver == reciever) || (it.sender == reciever && it.receiver == sender) }
        }

        // --------------------------------------------

        val listOrder = mutableListOf<Order>(
            Order(1, "morgan", "charles", "?"),
            Order(2, "morgan", "martin", "?"),
        )

        fun addOrder(o: Order) {
            listOrder.add(o)
        }

        fun getOrder(montir: String, customer: String): Order? {
            return listOrder.find { it.montir == montir && it.customer == customer }
        }

        fun getLastIndexOrder(): Int {
            return listOrder.last().id
        }

        fun deleteOrder(montir: String, customer: String) {
            listOrder.removeAll { it.montir == montir && it.customer == customer }
        }
    }
}