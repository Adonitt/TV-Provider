package org.example.finalproject.admin.repositories;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.*;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements Runnable {
    private final AdminRepository adminRepository;
    private final PackageRepository packageRepository;
    private final ChannelRepository channelRepository;
    private final ChannelService channelService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run() {
        if (adminRepository.count() == 0) {
            AdminEntity admin = new AdminEntity(1, "Adonit", "Halili", "1252334056", LocalDate.of(2005, 2, 12), "045350345", "Kosovo", "Ali Ajeti 72", "Podujeve", 11000, "adonit.halili@smart-tv.com", passwordEncoder.encode("Doni1234."), 19, AdminRole.SUPER_ADMIN, "/images/une.jpg", "M","Adonit Halili");
            adminRepository.save(admin);
        }

        if (packageRepository.count() == 0) {
            List<PackageEntity> packages = new ArrayList<>();
            packages.add(new PackageEntity(1, PackageEnum.BASIC, 15.99, 150, 150, "No", "Perfect for those who want the essentials, the Basic Package brings you a selection of the most popular and must-have channels for entertainment, news, and lifestyle.", "The Basic Package is ideal for families and individuals looking for straightforward, quality entertainment without unnecessary extras.", "/images/basic.avif", "A mix of local networks, top-rated news channels, and general entertainment options. Examples: ABC, NBC, FOX, CNN, Discovery, and more.", "Designed to fit your budget while offering great value.", "Enjoy crystal-clear quality on select channels.", "Access to a simple on-screen guide and parental controls"));
            packages.add(new PackageEntity(2, PackageEnum.SPORTIVE, 19.99, 190, 200, "Yes", "The Sports Package is a must-have for fans who never want to miss the action. From live games to expert analysis and highlights, this package delivers the ultimate sports experience right to your screen.", "Whether you’re cheering for your favorite team or keeping up with the latest in sports, the Sports Package ensures you’re always in the game.", "/images/sport.jpg", "A comprehensive selection of sports networks covering major leagues, international events, and niche sports. Examples: ESPN, FOX Sports, NBA TV, NFL Network, and more", "Live Coverage: Watch live games, tournaments, and exclusive matches across football, basketball, soccer, tennis, and more.", "Specialty Content:</b> Get access to behind-the-scenes coverage, player interviews, and in-depth analysis.", "Multi-Screen Access: </b> Watch games on your TV, tablet, or smartphone, so you never miss a moment."));
            packages.add(new PackageEntity(3, PackageEnum.PREMIUM, 24.99, 160, 180, "Yes", "The Premium Package is the ultimate entertainment experience, offering an extensive range of exclusive channels, blockbuster movies, and the latest series. Perfect for those who want the best of everything.", "The Premium Package is designed for viewers who demand more—more variety, more quality, and more of the content they love.", "/images/premium.webp", "Channels Included:</b> Access to premium networks and exclusive content providers. Examples: HBO, Showtime, Starz, Cinemax, and more.", "Exclusive Movies and Series: Watch the latest blockbuster films, award-winning series, and original productions you won’t find anywhere else.", " Enjoy uninterrupted entertainment on select channels and streaming services.", " Enjoy early access to new releases and special content bundles with select subscriptions."));
            packageRepository.saveAll(packages);
        }

        if (channelRepository.count() == 0) {
            String[] channelNames = {
                    "Info Channel", "RTK", "RTK 2", "ATV", "KTV", "RTV21", "Teve 1", "TV Dukagjini", "T 7",
                    "Kanal 10", "Klan Kosova", "DTV", "PRO 1", "Peace TV", "Shenja TV", "First Channel",
                    "Kanal D Drama", "Alsat M", "TVSH", "Vizion Plus", "Teve Aksion", "Teve Drama",
                    "Teve Fantasy", "Teve Comedy", "Teve Shqip", "Teve Novela", "DIZI", "Episode", "Prime",
                    "Kino 1", "Kino 2", "Kino 3", "Tring Action", "Tring Super", "Tring Fantasy", "SERIES HD",
                    "3 Plus", "Tring Life", "Tring Family HD", "Tring Shqip", "Tring Classic", "Tring Summertime",
                    "Tring Novelas", "Komedi", "Maxi", "Aksion", "Drame", "7 HD", "Cinema 1", "Cinema 2",
                    "Life HD", "Smile", "Tring Collection", "Tring Orginal", "Filmbox Premium", "Filmbox Family",
                    "Filmbox", "Filmbox Plus", "Filmbox Extra", "Filmbox Arthouse", "Star", "Star Life",
                    "Star Crime", "Star Movie", "Teve Kids 1", "Teve Kids 2", "TaoTao", "Prince Kids", "Sofia",
                    "Tip TV", "Bubble", "Tring Tring", "Tring Kids", "Baby TV", "Teve Doku 1", "Teve Doku 2",
                    "Doku 1", "Doku 2", "Gurmania", "24 Kitchen", "Muse", "Living HD", "Tera HD", "Tring World",
                    "Tring Histori", "Tring Planet", "Histrori (Galaxy)", "Shkence (Geo)", "Planet", "DOCUBOX",
                    "National Geographic", "National Geographic Wild", "Fashionbox", "Klan Macedonia", "UBT",
                    "Raho Channel HD", "Fax News", "ABC News", "Syri TV", "Arta News", "Kanal 7", "RTK 3", "RTK 4",
                    "MRT 2", "RTV 21 Macedonia", "Era TV", "RTV Islam", "Opoja", "Euro News Albanian", "RTI HD",
                    "TV Opinion", "TV Llapi", "TV Dielli", "TV Vali", "TV Tema", "TV Besa", "TV Liria",
                    "TV Mitrovica", "TV Prizreni", "Syri Vizion", "Diaspora TV", "TV Festina", "RTV Fontana HD",
                    "RTV Malisheva", "Istogu Channel", "Islam TV", "Plus TV", "Spin TV", "ALB UK", "ALB SE",
                    "Teuta", "Beat TV", "RTV 21 Mix", "RTV 21 Plus", "RTV 21 Popullore", "ON TV", "Folk +",
                    "MTV Kosova", "BBF", "TV Drita", "Amol HD", "Turbo Channel", "Ballkanika", "Elrodi", "TV R",
                    "Travelingo", "Positive Gold", "Suite TV", "Click", "360 Tunebox", "MTV", "MTV Hits",
                    "MTV Rock", "VH1 Hits", "VH1 Classic", "Al Jazzera Int", "Euro News", "France 24", "TRT World",
                    "TRT Arabiq", "RAI 1", "RAI 2", "KSB World", "RTL", "RTL2", "PRO 7", "SAT 1", "VOX",
                    "CABEL EINS", "NHK World Japan", "HABER TURK", "Cay TV", "TV 8", "Show HD", "Show MAX",
                    "Kanal D", "Start Turk", "ATV", "Kanal 7", "TV 8.5", "TRT Turk 1", "TRT 1", "TGRT Belgesel",
                    "TGRT EU", "TGRT Haber", "TRT Belgesel", "TRT Haber", "TRT Kurdi", "A Haber", "TV 4", "A2",
                    "TV 8 Int", "TRT Cocuk", "Ulke TV", "24 TV", "Dream Turk", "Power Turk", "TRT Muzik", "A SPOR",
                    "Dost TV", "HALK TV", "Tek Rumeli", "NTV", "Kral TV", "Federlana BiH", "Aljazera Balkans",
                    "Hayat HD", "Hayat Folk", "Hayat Plus", "RTV Novi Pazar", "OBN", "HRT1", "RTS 1", "RTS 2",
                    "RTS 3", "N1", "Prva", "B92", "TV Pink", "KCN1", "KCN2", "TV Hertz", "TV Kim", "TV Most",
                    "PrvaFiles", "PrvaKick", "PrvaWorld", "PrvaMax", "PrvaPlus", "TV Sallon Extra", "Super TV",
                    "RTCG", "Sanzak TV", "FightBox", "GameToon", "Fast & Fun Box", "Sport 1", "Sport 2",
                    "Sport 3", "Sport 4", "Sport 5", "Sport 6", "K Sport 1", "K Sport 2", "K Sport 3", "K Sport 4",
                    "K Sport 5", "Super Sport Kosova 1", "Super Sport Kosova 2", "Super Sport Kosova 3",
                    "Super Sport 1", "Super Sport 2", "Super Sport 3", "Super Sport 4", "Super Sport 5",
                    "Super Sport 6", "Super Sport 7"
            };

            List<ChannelEntity> channels = new ArrayList<>();
            for (String channelName : channelNames) {
                channels.add(new ChannelEntity(channelName)); // ID is auto-generated
            }

            channelRepository.saveAll(channels);
        }


    }


}
