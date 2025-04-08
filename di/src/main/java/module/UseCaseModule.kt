package module

import com.example.domain.usecase.GetCatsUseCase
import com.example.domain.usecase.GetCatsUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetCatsUseCase(useCaseImp: GetCatsUseCaseImp): GetCatsUseCase
}