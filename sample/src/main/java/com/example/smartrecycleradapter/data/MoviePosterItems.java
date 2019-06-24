package com.example.smartrecycleradapter.data;

/*
 * Created by Manne Öhlund on 2019-06-19.
 * Copyright (c) All rights reserved.
 */

import com.example.smartrecycleradapter.R;

import java.util.Random;

public class MoviePosterItems {

    public static final int[] POSTER_ITEMS = new int[]{
            R.mipmap.poster_guardians_of_the_galaxy_vol_two,
            R.mipmap.poster_spiderman_far_from_home,
            R.mipmap.poster_the_last_jedi,
            R.mipmap.poster_han_solo,
            R.mipmap.poster_terminator_dark_fate,
            R.mipmap.poster_thor_ragnarok,
            R.mipmap.poster_grinch,
            R.mipmap.poster_logan,
            R.mipmap.poster_alien_covenant_alt,
            R.mipmap.poster_avengers_endgame,
            R.mipmap.poster_first_man,
            R.mipmap.poster_ice_age_collision,
            R.mipmap.poster_john_wick3,
            R.mipmap.poster_predator,
            R.mipmap.poster_rambo4,
            R.mipmap.poster_aladdin,
            R.mipmap.poster_aladdin_alt,
            R.mipmap.poster_joker,
            R.mipmap.poster_murder_mystery,
            R.mipmap.poster_pokemon_detective_pikachu,
            R.mipmap.poster_the_lion_king,
            R.mipmap.poster_toy_story_4,
            R.mipmap.poster_xmen_dark_phoenix,
            R.mipmap.poster_dora};

    public static int getRandomPoster() {
        return POSTER_ITEMS[new Random().nextInt(POSTER_ITEMS.length)];
    }

    public static final int[] BANNER_ITEMS = new int[]{
            R.mipmap.banner_300_rise_of_an_empire,
            R.mipmap.banner_alien_covenant,
            R.mipmap.banner_alien_easter,
            R.mipmap.banner_blaerunner,
            R.mipmap.banner_bond,
            R.mipmap.banner_dark_knight,
            R.mipmap.banner_expendables,
            R.mipmap.banner_finding_dory,
            R.mipmap.banner_interstellar,
            R.mipmap.banner_interstellar_quad,
            R.mipmap.banner_iron_man_alt,
            R.mipmap.banner_jurassic_park_red_big,
            R.mipmap.banner_jurassic_park_redironman,
            R.mipmap.banner_lort,
            R.mipmap.banner_man_of_steel,
            R.mipmap.banner_mi_fallout,
            R.mipmap.banner_new_star_wars,
            R.mipmap.banner_terminator,
            R.mipmap.banner_thor_ragnarok};

    public static int getRandomBanner() {
        return BANNER_ITEMS[new Random().nextInt(BANNER_ITEMS.length)];
    }

    public static final int[] COMING_SOON_ITEMS = new int[]{
            R.mipmap.first_man_thumb,
            R.mipmap.terminator_dark_fate_thumb,
            R.mipmap.thumbmurder_mystery,
            R.mipmap.joker_thumb,
            R.mipmap.thumb_lion_king,
            R.mipmap.woderwoman_thumb,
            R.mipmap.it_2_thumb,
            R.mipmap.john_wick_3_thumb,
            R.mipmap.ferdinand_thumb,
            R.mipmap.grinch_thumb,
            R.mipmap.hellboy_thumb,
            R.mipmap.age_of_uprising_thumb,
            R.mipmap.beachbumposter_thumb,
            R.mipmap.bernadette_thumb,
            R.mipmap.bohemian_rhapsody_thumb,
            R.mipmap.once_hollywoodthumb,
            R.mipmap.rambo_last_blood_thumb,
            R.mipmap.predator_thumb,
            R.mipmap.godzilla_king_of_the_monsters_thumb,
            R.mipmap.godzilla_thumb,
            R.mipmap.abominable_thumb
    };

    public static final int[] ACTION_ITEMS = new int[]{
            R.mipmap.fury_thumb,
            R.mipmap.edge_of_tomorrow_thumb,
            R.mipmap.hellboy_thumb,
            R.mipmap.godzilla_king_of_the_monsters_thumb,
            R.mipmap.threehundred_thumb,
            R.mipmap.guardians_of_the_galaxy_thumb,
            R.mipmap.age_of_uprising_thumb,
            R.mipmap.deadsnow_thumb,
            R.mipmap.matrix_thumb,
            R.mipmap.godzilla_thumb,
            R.mipmap.hercules_thumb,
            R.mipmap.mummy_thumb,
            R.mipmap.hobbit_thumb,
            R.mipmap.dracula_untold_thumb
    };

    public static final int[] ADVENTURE_ITEMS = new int[]{
            R.mipmap.hercules_thumb,
            R.mipmap.hobbit_thumb,
            R.mipmap.mummy_thumb,
            R.mipmap.age_of_uprising_thumb,
            R.mipmap.hellboy_thumb,
            R.mipmap.matrix_thumb,
            R.mipmap.dracula_untold_thumb,
            R.mipmap.threehundred_thumb,
            R.mipmap.guardians_of_the_galaxy_thumb,
            R.mipmap.godzilla_king_of_the_monsters_thumb,
            R.mipmap.godzilla_thumb
    };

    public static final int[] SCI_FI_ITEMS = new int[]{
            R.mipmap.interstellar_thumb,
            R.mipmap.matrix_thumb,
            R.mipmap.edge_of_tomorrow_thumb,
            R.mipmap.adastra_thumb,
            R.mipmap.avengers_endgame_thumb_1,
            R.mipmap.avengers_endgame_thumb_2,
            R.mipmap.avengers_endgame_thumb_3
    };

    public static final int[] ANIM_ITEMS = new int[]{
            R.mipmap.thumb_lion_king,
            R.mipmap.dora_thumb,
            R.mipmap.frozen2_thumb,
            R.mipmap.ferdinand_thumb,
            R.mipmap.grinch_thumb,
            R.mipmap.incredibles_2_thumb,
            R.mipmap.abominable_thumb,
            R.mipmap.how_to_train_your_dragon_two_thumb,
            R.mipmap.incredibles_thumb,
            R.mipmap.angry_birds_movie_two_thumb
    };
}
