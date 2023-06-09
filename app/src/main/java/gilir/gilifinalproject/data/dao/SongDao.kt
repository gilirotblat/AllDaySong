package gilir.gilifinalproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import gilir.gilifinalproject.models.FavoriteSong
import gilir.gilifinalproject.models.artistapi.Artist
import gilir.gilifinalproject.models.playlistApi.Playlist
import gilir.gilifinalproject.models.songsapi.Song


@Dao
interface SongDao {

    @Update
    suspend fun updateSong(song: Song)

    //add
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllSongs(songsList: List<Song>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllArtists(artistList: List<Artist>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllPlaylists(songsList: List<Playlist>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllArtistSongs(songsList: List<Song>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllPlaylistsSong(songsList: List<Song>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteSongs(songsList: List<Song>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteSong(id: FavoriteSong)

    //getters
    @Query("SELECT * FROM songs")
    fun getAllSongs(): LiveData<List<Song>>

    @Query("SELECT * FROM songs")
    suspend fun getAllSongsNotLive(): List<Song>

    @Query("SELECT * FROM songs WHERE id = :id")
    fun getSongByID(id: Int): Song

    @Query("SELECT * FROM artists")
    fun getAllArtists(): LiveData<List<Artist>>

    @Query("SELECT * FROM Playlist")
    fun getAllPlaylists(): LiveData<List<Playlist>>

    //@Query("SELECT * FROM songs WHERE ")

    //    @Query("SELECT * FROM songs WHERE isClicked = 1")
//    fun getFavoriteSong(): LiveData<List<Song>>
    @Query("SELECT * FROM `fab songs`")
    fun getFavoriteSongsIds(): LiveData<List<FavoriteSong>>

    @Query("SELECT * FROM songs INNER JOIN `fab songs` ON songs.id = `fab songs`.id")
    suspend fun getAllFavoriteSongs(): List<Song>


    @Query("SELECT * FROM songs WHERE artist =:artistId")
    fun getArtistSong(artistId: Int): LiveData<List<Song>>

    //sorted

    @Query("SELECT * FROM songs ORDER BY duration DESC")
    fun songByDuration(): LiveData<List<Song>>

    @Query("SELECT * FROM songs ORDER BY title DESC")
    fun songByTitle(): LiveData<List<Song>>


    // @Query("SELECT * FROM songs WHERE Artist ORDER BY title DESC")
    // fun artistByName(): LiveData<List<Artist>>


}