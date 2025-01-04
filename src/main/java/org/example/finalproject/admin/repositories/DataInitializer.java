package org.example.finalproject.admin.repositories;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.models.admin.Packages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements Runnable {
    private final AdminRepository adminRepository;
    private final PackageRepository packageRepository;

    @Override
    public void run() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin(1, "Adonit", "Halili", "1252334056", LocalDate.of(2005, 2, 12), "045350345", "Kosovo", "Ali Ajeti 72", "Podujeve", 11000, "adonit.halili@smart-tv.com", "Doni1234.", 19, "Super Admin", "/assets-a/img/656cb1bb-8734-481e-b432-037578ba7ffc_une.jpg", "M");
            adminRepository.save(admin);
        }

        if (packageRepository.count() == 0) {
            List<Packages> packages = new ArrayList<>();
            packages.add(new Packages(1, "Basic", 15.99, 150, 150, "No", "Perfect for those who want the essentials, the Basic Package brings you a selection of the most popular and must-have channels for entertainment, news, and lifestyle.", "The Basic Package is ideal for families and individuals looking for straightforward, quality entertainment without unnecessary extras.", "/images/basic.avif", "A mix of local networks, top-rated news channels, and general entertainment options. Examples: ABC, NBC, FOX, CNN, Discovery, and more.", "Designed to fit your budget while offering great value.", "Enjoy crystal-clear quality on select channels.", "Access to a simple on-screen guide and parental controls"));
            packages.add(new Packages(2, "Sportive", 19.99, 190, 200, "Yes", "The Sports Package is a must-have for fans who never want to miss the action. From live games to expert analysis and highlights, this package delivers the ultimate sports experience right to your screen.", "Whether you’re cheering for your favorite team or keeping up with the latest in sports, the Sports Package ensures you’re always in the game.", "/images/sport.jpg", "A comprehensive selection of sports networks covering major leagues, international events, and niche sports. Examples: ESPN, FOX Sports, NBA TV, NFL Network, and more", "Live Coverage: Watch live games, tournaments, and exclusive matches across football, basketball, soccer, tennis, and more.", "Specialty Content:</b> Get access to behind-the-scenes coverage, player interviews, and in-depth analysis.", "Multi-Screen Access: </b> Watch games on your TV, tablet, or smartphone, so you never miss a moment."));
            packages.add(new Packages(3, "Premium", 24.99, 160, 180, "Yes", "The Premium Package is the ultimate entertainment experience, offering an extensive range of exclusive channels, blockbuster movies, and the latest series. Perfect for those who want the best of everything.", "The Premium Package is designed for viewers who demand more—more variety, more quality, and more of the content they love.", "/images/premium.webp", "Channels Included:</b> Access to premium networks and exclusive content providers. Examples: HBO, Showtime, Starz, Cinemax, and more.", "Exclusive Movies and Series: Watch the latest blockbuster films, award-winning series, and original productions you won’t find anywhere else.", " Enjoy uninterrupted entertainment on select channels and streaming services.", " Enjoy early access to new releases and special content bundles with select subscriptions."));
            packageRepository.saveAll(packages);
        }
    }


}
