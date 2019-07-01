package com.example.smartrecycleradapter.data

/*
 * Created by Manne Öhlund on 2019-06-19.
 * Copyright (c) All rights reserved.
 */

import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.MovieModel
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList

object MovieDataItems {

    // Coming soon
    val comingSoonItems: List<Any> = asList(
            MovieModel("Fist man", R.mipmap.first_man_thumb),
            MovieModel("Terminator dark fate", R.mipmap.terminator_dark_fate_thumb),
            MovieModel("Murder Mystery", R.mipmap.thumbmurder_mystery),
            MovieModel("Joker", R.mipmap.joker_thumb),
            MovieModel("The Lion king", R.mipmap.thumb_lion_king),
            MovieModel("Wonder Woman", R.mipmap.woderwoman_thumb),
            MovieModel("It 2", R.mipmap.it_2_thumb),
            MovieModel("John Wick 3", R.mipmap.john_wick_3_thumb),
            MovieModel("Grinch", R.mipmap.grinch_thumb),
            MovieModel("Hellboy", R.mipmap.hellboy_thumb),
            MovieModel("Age of uprising", R.mipmap.age_of_uprising_thumb),
            MovieModel("Beachbum", R.mipmap.beachbumposter_thumb),
            MovieModel("Bernadette", R.mipmap.bernadette_thumb),
            MovieModel("Bohemian rhapsody", R.mipmap.bohemian_rhapsody_thumb),
            MovieModel("Once upon a time in Hollywood", R.mipmap.once_hollywoodthumb),
            MovieModel("Rambo last blood", R.mipmap.rambo_last_blood_thumb),
            MovieModel("Predator", R.mipmap.predator_thumb),
            MovieModel("Godzilla king of the mosters", R.mipmap.godzilla_king_of_the_monsters_thumb),
            MovieModel("Godzilla", R.mipmap.godzilla_thumb),
            MovieModel("Abominable", R.mipmap.abominable_thumb)
    )

    // My watchlist
    val myWatchListItems: List<Any> = asList(
            MovieModel("Joker", R.mipmap.joker_thumb),
            MovieModel("Terminator dark fate", R.mipmap.terminator_dark_fate_thumb),
            MovieModel("John Wick 3", R.mipmap.john_wick_3_thumb),
            MovieModel("Bohemian rhapsody", R.mipmap.bohemian_rhapsody_thumb),
            MovieModel("Fist man", R.mipmap.first_man_thumb),
            MovieModel("Once upon a time in Hollywood", R.mipmap.once_hollywoodthumb),
            MovieModel("Murder Mystery", R.mipmap.thumbmurder_mystery),
            MovieModel("It 2", R.mipmap.it_2_thumb),
            MovieModel("Hellboy", R.mipmap.hellboy_thumb),
            MovieModel("Beachbum", R.mipmap.beachbumposter_thumb),
            MovieModel("Predator", R.mipmap.predator_thumb),
            MovieModel("Rambo last blood", R.mipmap.rambo_last_blood_thumb)
    )

    // Action
    var nestedActionItems: List<Any> = asList(
            MovieModel("Fury", R.mipmap.fury_thumb),
            MovieModel("Edge of tomorrow", R.mipmap.edge_of_tomorrow_thumb),
            MovieModel("Godzilla king of the mosters", R.mipmap.godzilla_king_of_the_monsters_thumb),
            MovieModel("Predator", R.mipmap.predator_thumb),
            MovieModel("Godzilla", R.mipmap.godzilla_thumb),
            MovieModel("Rambo last blood", R.mipmap.rambo_last_blood_thumb),
            MovieModel("Hellboy", R.mipmap.hellboy_thumb),
            MovieModel("Terminator dark fate", R.mipmap.terminator_dark_fate_thumb),
            MovieModel("300 Rise of an empire", R.mipmap.threehundred_thumb),
            MovieModel("Guardians of the galaxy", R.mipmap.guardians_of_the_galaxy_thumb),
            MovieModel("Age of uprising", R.mipmap.age_of_uprising_thumb),
            MovieModel("Deadsnow", R.mipmap.deadsnow_thumb),
            MovieModel("Matrix", R.mipmap.matrix_thumb),
            MovieModel("Mummy", R.mipmap.mummy_thumb),
            MovieModel("Hercules", R.mipmap.hercules_thumb),
            MovieModel("Hobbit", R.mipmap.hobbit_thumb),
            MovieModel("Dracula untold", R.mipmap.dracula_untold_thumb)
    )

    // Adventure
    var nestedAdventureItems: List<Any> = asList(
            MovieModel("Hercules", R.mipmap.hercules_thumb),
            MovieModel("Mummy", R.mipmap.mummy_thumb),
            MovieModel("Age of uprising", R.mipmap.age_of_uprising_thumb),
            MovieModel("Matrix", R.mipmap.matrix_thumb),
            MovieModel("Hellboy", R.mipmap.hellboy_thumb),
            MovieModel("Dracula untold", R.mipmap.dracula_untold_thumb),
            MovieModel("300 Rise of an empire", R.mipmap.threehundred_thumb),
            MovieModel("Guardians of the galaxy", R.mipmap.guardians_of_the_galaxy_thumb),
            MovieModel("Godzilla king of the mosters", R.mipmap.godzilla_king_of_the_monsters_thumb),
            MovieModel("Godzilla", R.mipmap.godzilla_thumb)
    )

    // Sci-Fi
    var nestedSciFiItems: List<Any> = asList(
            MovieModel("Godzilla", R.mipmap.interstellar_thumb),
            MovieModel("Matrix", R.mipmap.matrix_thumb),
            MovieModel("Edge of tomorrow", R.mipmap.edge_of_tomorrow_thumb),
            MovieModel("Ad Astra", R.mipmap.adastra_thumb),
            MovieModel("Avengers endgame", R.mipmap.avengers_endgame_thumb_1),
            MovieModel("Avengers endgame", R.mipmap.avengers_endgame_thumb_2),
            MovieModel("Avengers endgame", R.mipmap.avengers_endgame_thumb_3)
    )

    // Animated
    var nestedAnimatedItems: List<Any> = asList(
            MovieModel("The Lion king", R.mipmap.thumb_lion_king),
            MovieModel("Dora", R.mipmap.dora_thumb),
            MovieModel("Frozen 2", R.mipmap.frozen2_thumb),
            MovieModel("Ferdinand", R.mipmap.ferdinand_thumb),
            MovieModel("The Grinch", R.mipmap.grinch_thumb),
            MovieModel("Incredibles 2", R.mipmap.incredibles_2_thumb),
            MovieModel("Abominable", R.mipmap.abominable_thumb),
            MovieModel("How to train your dragon 2", R.mipmap.how_to_train_your_dragon_two_thumb),
            MovieModel("Incredibles 2", R.mipmap.incredibles_thumb),
            MovieModel("Angry birds 2", R.mipmap.angry_birds_movie_two_thumb)

    )

    // Recent viewed items
    var nestedRecentViewedItems = ArrayList<Any>()
    init {
        nestedRecentViewedItems.addAll(comingSoonItems)
        nestedRecentViewedItems.add(MovieModel("Dumb and dumber 2", R.mipmap.dumb_and_dumber_two_thumb))
        nestedRecentViewedItems.add(MovieModel("Her", R.mipmap.her_thumb))
        nestedRecentViewedItems.add(MovieModel("Imitation game", R.mipmap.imitation_game_thumb))
        nestedRecentViewedItems.add(MovieModel("Interview", R.mipmap.interview_thumb))
        nestedRecentViewedItems.add(MovieModel("As above so below", R.mipmap.as_above_so_below_thumb))
    }

    private val POSTER_ITEMS = intArrayOf(
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
            R.mipmap.poster_dora)

    val randomPoster: Int
        get() = POSTER_ITEMS[Random().nextInt(POSTER_ITEMS.size)]

    private val BANNER_ITEMS = intArrayOf(
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
            R.mipmap.banner_thor_ragnarok)

    val randomBanner: Int
        get() = BANNER_ITEMS[Random().nextInt(BANNER_ITEMS.size)]
}
