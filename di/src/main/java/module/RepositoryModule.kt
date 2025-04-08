package module

import com.example.data.service.repository.CatRepositoryImp
import com.example.domain.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesCatRepository(repositoryImp: CatRepositoryImp): CatRepository
}