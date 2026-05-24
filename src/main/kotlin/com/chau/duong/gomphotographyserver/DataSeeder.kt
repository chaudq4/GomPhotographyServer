package com.chau.duong.gomphotographyserver

import com.chau.duong.gomphotographyserver.controller.AlbumController
import com.chau.duong.gomphotographyserver.controller.AlbumUserController
import com.chau.duong.gomphotographyserver.controller.DriveController
import com.chau.duong.gomphotographyserver.controller.UserController
import com.chau.duong.gomphotographyserver.entity.Album
import com.chau.duong.gomphotographyserver.entity.AlbumUser
import com.chau.duong.gomphotographyserver.entity.PhotoPackage
import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.service.PhotoPackageService
import jakarta.transaction.Transactional
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.util.*

@Component
class DataSeeder(
    private val albumController: AlbumController,
    private val albumUserController: AlbumUserController,
    private val userController: UserController,
    private val photoPackageService: PhotoPackageService,
    private val driveController: DriveController
) : CommandLineRunner {
    val scope = CoroutineScope(Dispatchers.IO)

    @Transactional
    override fun run(vararg args: String) {
//        scope.launch {
//            try {
//                val result =
//                    driveController.getImages("https://drive.google.com/drive/folders/19dBJCca3OMmgtt8Eu3Qv7YhLWkj_q3fb?usp=sharing")
//                println(result.size)
//            } catch (e: WebClientResponseException) {
//                println(e.responseBodyAsString)
//            }
//        }
//        fakePhotoPackage()
//        fakeUser()
//        fakeAlbum()
//        fakeAlbumUser()
        scope.launch {
            albumController.syncAllAlbum()
        }
    }

    fun fakeAlbumUser() {
        val albumUsers = listOf(
            AlbumUser(user = User(1), album = Album(1)),
            AlbumUser(user = User(2), album = Album(1)),
            AlbumUser(user = User(3), album = Album(2)),
            AlbumUser(user = User(4), album = Album(2)),
            AlbumUser(user = User(5), album = Album(3)),
            AlbumUser(user = User(6), album = Album(3)),
            AlbumUser(user = User(7), album = Album(4)),
            AlbumUser(user = User(8), album = Album(4)),
            AlbumUser(user = User(9), album = Album(5)),
            AlbumUser(user = User(10), album = Album(5)),
            AlbumUser(user = User(1), album = Album(6)),
            AlbumUser(user = User(2), album = Album(7)),
            AlbumUser(user = User(3), album = Album(8)),
            AlbumUser(user = User(4), album = Album(6)),
            AlbumUser(user = User(5), album = Album(7)),
        )
        albumUsers.forEach {
            albumUserController.addAlbumUser(it)
        }
    }

    fun fakeAlbum() {

        val albums = listOf(

            Album(
                date = "12/03/2026",
                name = "MN",
                linkOriginal = "https://drive.google.com/drive/folders/1NGIz_9B4h85zlNlZWkrHisUIyNdHfB99?usp=sharing",
                linkEdit = "",
            ),

            Album(
                date = "02/01/2025",
                name = "Quang Sử",
                linkOriginal = "https://drive.google.com/drive/folders/1v48pzpx0B_sBE4Xd77BoDnDq8oao9Ucn?usp=drive_link",
                linkEdit = "https://drive.google.com/drive/folders/1fY5U7GmO78T4DeSADGqRF7b2Wn_jOPeh?usp=drive_link",
            ),

            Album(
                date = "15/03/2140",
                name = "Ninh Hà",
                linkOriginal = "https://drive.google.com/drive/folders/1fcUzBUIy-U9t7lR-l4shRvjtgXTXBczM?usp=drive_link",
                linkEdit = "https://drive.google.com/drive/folders/1YDIBHn2jvlpV7vWpH0v-fxPWdvEZy41a?usp=drive_link",
            ),

            Album(
                date = "19/08/2025",
                name = "Kim Anh",
                linkOriginal = "https://drive.google.com/drive/folders/1983AODx8T4n-q_AiAyHJwsjGRrNwtoe-?usp=drive_link",
                linkEdit = "https://drive.google.com/drive/folders/193gd0ee2WXn1X2pQwuF4YWSE8umfHiyk?usp=drive_link",
            ),

            Album(
                date = "25/03/2026",
                name = "C Ly",
                linkOriginal = "https://drive.google.com/drive/folders/1QTtEcy-6sAOCVmnxxKEGK8pJyuv5Yu7v?usp=drive_link",
                linkEdit = "",
            ),

            Album(
                date = "22/12/2026",
                name = "Thinh",
                linkOriginal = "https://drive.google.com/drive/folders/1TGQFijotR8CAr2ixN8ljKG_tzf0fkeP-?usp=drive_link",
                linkEdit = "",
            ),

            Album(
                date = "03/01/2026",
                name = "Mai Văn Bình",
                linkOriginal = "https://drive.google.com/drive/folders/1983AODx8T4n-q_AiAyHJwsjGRrNwtoe-?usp=drive_link",
                linkEdit = "https://drive.google.com/drive/folders/1d5U74bjgEk4Bt0EqdIAPl-UhvVnpTXff?usp=drive_link",
            ),

            Album(
                date = "03/11/2011",
                name = "Thu Thảo",
                linkOriginal = "https://drive.google.com/drive/folders/1lBlZsVeQ4KdW27k0492p54WGlLfpickv?usp=drive_link",
                linkEdit = "https://drive.google.com/drive/folders/1bcJevL4Ri2T6E8uXgom1x6DawQYa7G4G?usp=drive_link",
            )
        )
        albums.forEach {
            albumController.addAlbum(it)
        }

        println("Fake albums inserted")
    }

    fun fakeUser() {
        val users = listOf(

            User(
                username = "admin",
                password = "123456",
                name = "Administrator",
                email = "admin@gmail.com",
                phone = "0900000001"
            ),

            User(
                username = "minhtran",
                password = "123456",
                name = "Minh Trần",
                email = "minhtran@gmail.com",
                phone = "0900000002"
            ),

            User(
                username = "hoangnguyen",
                password = "123456",
                name = "Hoàng Nguyễn",
                email = "hoangnguyen@gmail.com",
                phone = "0900000003"
            ),

            User(
                username = "lananh",
                password = "123456",
                name = "Lan Anh",
                email = "lananh@gmail.com",
                phone = "0900000004"
            ),

            User(
                username = "ducpham",
                password = "123456",
                name = "Đức Phạm",
                email = "ducpham@gmail.com",
                phone = "0900000005"
            ),

            User(
                username = "huyle",
                password = "123456",
                name = "Huy Lê",
                email = "huyle@gmail.com",
                phone = "0900000006"
            ),

            User(
                username = "trangvo",
                password = "123456",
                name = "Trang Võ",
                email = "trangvo@gmail.com",
                phone = "0900000007"
            ),

            User(
                username = "khanhdo",
                password = "123456",
                name = "Khánh Đỗ",
                email = "khanhdo@gmail.com",
                phone = "0900000008"
            ),

            User(
                username = "linhpham",
                password = "123456",
                name = "Linh Phạm",
                email = "linhpham@gmail.com",
                phone = "0900000009"
            ),

            User(
                username = "vietdao",
                password = "123456",
                name = "Việt Đào",
                email = "vietdao@gmail.com",
                phone = "0900000010"
            )
        )
        users.forEach {
            userController.createUser(it)
        }
    }

    fun getAlbums(): Album {
        val random = Random()
        val index = random.nextInt()
        return Album(
            date = "25/03/2026",
            name = "Name album $index",
            linkOriginal = "Link original $index",
            linkEdit = "Edit album $index"
        )
    }

    fun fakePhotoPackage() {
        val packages = listOf(
            // ================= ĐÁM CƯỚI =================

            PhotoPackage(
                catalog = "Đám cưới",
                name = "Wedding Basic",
                description = "Gói cưới cơ bản chụp trong ngày.",
                price = 8000000,
                durationHours = 8,
                editedPhotos = 80,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Đám cưới",
                name = "Wedding Premium",
                description = "Gói cưới cao cấp full day cinematic.",
                price = 18000000,
                durationHours = 12,
                editedPhotos = 200,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Đám cưới",
                name = "Luxury Wedding",
                description = "Concept cưới luxury cao cấp.",
                price = 35000000,
                durationHours = 15,
                editedPhotos = 350,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1507504031003-b417219a0fde?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Đám cưới",
                name = "Pre Wedding Outdoor",
                description = "Chụp pre wedding ngoại cảnh.",
                price = 12000000,
                durationHours = 10,
                editedPhotos = 120,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?q=80&w=1200&auto=format&fit=crop"
            ),

            // ================= CÁ NHÂN =================

            PhotoPackage(
                catalog = "Cá nhân",
                name = "Portrait Studio",
                description = "Chụp chân dung studio chuyên nghiệp.",
                price = 1500000,
                durationHours = 2,
                editedPhotos = 10,
                rawPhotos = false,
                makeupIncluded = false,
                outdoor = false,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Cá nhân",
                name = "Fashion Street",
                description = "Concept thời trang đường phố.",
                price = 4200000,
                durationHours = 5,
                editedPhotos = 35,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Cá nhân",
                name = "Outdoor Lifestyle",
                description = "Concept lifestyle ngoài trời.",
                price = 3000000,
                durationHours = 4,
                editedPhotos = 25,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1496747611176-843222e1e57c?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Cá nhân",
                name = "Birthday Concept",
                description = "Concept sinh nhật setup studio.",
                price = 2500000,
                durationHours = 3,
                editedPhotos = 20,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = false,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1464349153735-7db50ed83c84?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Cá nhân",
                name = "Travel Blogger",
                description = "Concept du lịch và blogger.",
                price = 5000000,
                durationHours = 6,
                editedPhotos = 40,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?q=80&w=1200&auto=format&fit=crop"
            ),

            // ================= DOANH NGHIỆP =================

            PhotoPackage(
                catalog = "Doanh nghiệp",
                name = "Company Profile",
                description = "Chụp profile doanh nghiệp.",
                price = 10000000,
                durationHours = 6,
                editedPhotos = 60,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Doanh nghiệp",
                name = "Corporate Event",
                description = "Chụp sự kiện doanh nghiệp.",
                price = 15000000,
                durationHours = 10,
                editedPhotos = 150,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Doanh nghiệp",
                name = "Product Photography",
                description = "Chụp sản phẩm ecommerce.",
                price = 5000000,
                durationHours = 4,
                editedPhotos = 50,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = false,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1512436991641-6745cdb1723f?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Doanh nghiệp",
                name = "Cafe Branding",
                description = "Branding quán cafe và menu.",
                price = 6500000,
                durationHours = 5,
                editedPhotos = 60,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1509042239860-f550ce710b93?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Doanh nghiệp",
                name = "Hotel & Resort",
                description = "Chụp resort và khách sạn.",
                price = 25000000,
                durationHours = 14,
                editedPhotos = 250,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=1200&auto=format&fit=crop"
            ),
            //=================Kỷ yếu===========
            PhotoPackage(
                catalog = "Kỷ yếu",
                name = "Kỷ Yếu Basic",
                description = "Gói chụp kỷ yếu cơ bản cho lớp học.",
                price = 5500000,
                durationHours = 5,
                editedPhotos = 80,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Kỷ yếu",
                name = "Kỷ Yếu Premium",
                description = "Gói kỷ yếu cao cấp với nhiều concept.",
                price = 12000000,
                durationHours = 10,
                editedPhotos = 250,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1523240795612-9a054b0db644?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Kỷ yếu",
                name = "Concept Thanh Xuân",
                description = "Concept thanh xuân học đường tự nhiên.",
                price = 8500000,
                durationHours = 7,
                editedPhotos = 160,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Kỷ yếu",
                name = "Kỷ Yếu Studio",
                description = "Chụp kỷ yếu trong studio chuyên nghiệp.",
                price = 6500000,
                durationHours = 4,
                editedPhotos = 100,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = false,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Kỷ yếu",
                name = "Kỷ Yếu Flycam",
                description = "Gói kỷ yếu quay flycam cinematic.",
                price = 15000000,
                durationHours = 12,
                editedPhotos = 300,
                rawPhotos = true,
                makeupIncluded = true,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?q=80&w=1200&auto=format&fit=crop"
            ),
            //=========================Sự kiện====================
            PhotoPackage(
                catalog = "Sự kiện",
                name = "Event Basic",
                description = "Gói chụp sự kiện cơ bản cho hội nghị và sinh nhật.",
                price = 5000000,
                durationHours = 4,
                editedPhotos = 80,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = false,
                thumbnail = "https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Sự kiện",
                name = "Conference Premium",
                description = "Chụp hội nghị và workshop chuyên nghiệp.",
                price = 12000000,
                durationHours = 8,
                editedPhotos = 180,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = false,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1505373877841-8d25f7d46678?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Sự kiện",
                name = "Music Festival",
                description = "Chụp lễ hội âm nhạc và concert.",
                price = 20000000,
                durationHours = 12,
                editedPhotos = 350,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Sự kiện",
                name = "Birthday Event",
                description = "Concept tiệc sinh nhật và party.",
                price = 6500000,
                durationHours = 5,
                editedPhotos = 120,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = false,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1464366400600-7168b8af9bc3?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Sự kiện",
                name = "Grand Opening",
                description = "Chụp khai trương cửa hàng và thương hiệu.",
                price = 9000000,
                durationHours = 6,
                editedPhotos = 140,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1517457373958-b7bdd4587205?q=80&w=1200&auto=format&fit=crop"
            ),

            PhotoPackage(
                catalog = "Sự kiện",
                name = "Team Building",
                description = "Gói chụp team building ngoài trời.",
                price = 11000000,
                durationHours = 8,
                editedPhotos = 220,
                rawPhotos = true,
                makeupIncluded = false,
                outdoor = true,
                videoIncluded = true,
                thumbnail = "https://images.unsplash.com/photo-1529156069898-49953e39b3ac?q=80&w=1200&auto=format&fit=crop"
            )
        )
        packages.forEach {
            photoPackageService.addPhotoPackage(it)
        }
    }
}