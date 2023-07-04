<template>
	<div class="block-1">
		<div class="logo" style="background-image: url(img/logo.svg);"></div>

		<section class="layers">

			<div class="layers__container">

				<div class="layers__item layer1" style="background-image: url(img/layer-1.jpg);"></div>
				<div class="layers__item layer2" style="background-image: url(img/layer-2.png);"></div>
				<div class="layers__item layer3">
					<div class="hero-content">
						<h1>Справочник сведений ЦБ РФ <span>о кредитно-финансовых учреждениях</span></h1>
						<div class="hero-content__p">Используйте справочник в удобном для себя формате</div>
						<router-link :to="{name: 'Handbook'}">
							<button class="button-start" >ПЕРЕЙТИ</button>
						</router-link>
					</div>
				</div>
				<div class="layers__item layer4" style="background-image: url(img/layer-4.png);"></div>

			</div>

		</section>
	</div>

	<div class="block-2">
		<div class="inovation-block">
			<div class="textarea-block">
				<h1>Интерактивный <span>дизайн</span></h1><br />
				<p>Мы продумали все до мелочей, чтобы вам было удобно и комфортно пользоваться нашими услугами.</p>
			</div>
			<div class="ilutration" style="background-image: url(img/illustration.svg);">
			</div>
		</div>
		<div class="security-block">
			<div class="convenient-reference">
				<h1>ЭТО ПРОСТО УДОБНЫЙ </h1>
				<router-link :to="{name: 'Handbook'}">
					<button>Справочник</button>
				</router-link>
				
			</div>
			<div class="textarea-block">
				<h1>БЕЗОПАСНОСТЬ И ПРОЗРАЧНОСТЬ</h1>
				<p>В нашем справочнике вы найдете только актуальные и проверенные данные, что гарантирует надежность и
					безопасность информации.</p>
			</div>
		</div>
	</div>

	<div class="block-3">
		<h1>ИСПЫТАЙ ВСЕ <span>ВОЗМОЖНОСТИ</span></h1>
		<div class="container-opportunities">


			<div class="opportunitie">
				<h2>СОВРЕМЕННЫЙ ИНТЕРФЕЙС</h2>
				<p>Наш сайт предоставляет пользователю интуитивно понятный и динамический интерфейс. Пользуйтесь фильтрацией
					данных, вкладкой “избранные” и многими другими функциями. Опыт использования нашего ресурса - это не просто
					поиск данных, это погружение в мир информации.</p>
			</div>
			<div class="opportunitie">
				<h2>БЫСТРОДЕЙСТВИЕ САЙТА</h2>
				<p>Мы ценим ваше время. Сайт оптимизирован для обеспечения быстрого отклика и минимизации времени загрузки
					страниц, что позволяет вам быстро получать нужную информацию. Независимо от объема данных, которые вы хотите
					обработать, наша платформа всегда готова к работе.</p>
			</div>
			<div class="opportunitie">
				<h2> ПОЛУЧИТЬ API УЧРЕЖДЕНИЙ</h2>
				<p>Предоставляем возможность доступа к API для автоматического получения и обновления данных. Это отличное решение
					для компаний и разработчиков, которым требуется интеграция нашей базы данных с их собственными системами.</p>
				<button>Подробнее</button>
			</div>
			<div class="opportunitie">
				<h2>ВОЗМОЖНОСТЬ ОТСЛЕЖИВАНИЯ</h2>
				<p>Наша платформа предлагает уникальный инструмент для отслеживания изменений в кредитно-финансовых учреждениях.
					. Будьте в курсе всех обновлений, следите за динамикой и
					прогнозируйте тренды.</p>
			</div>
		</div>
	</div>
	<div class="block-4">
		<h1>ЗАРЕГИСТРИРУЙСЯ <span>И ПОПРОБУЙ САМ</span></h1>
			<button>РЕГИСТРАЦИЯ</button>
		
	</div>
	
</template>

<script>
export default{
	mounted() {
        document.body.style.backgroundColor = '#5A77DF',
        document.body.style.color = '#FEFFFF'

    },
    beforeDestroy() {
        document.body.style.backgroundColor = '#FFFFFF',
				document.body.style.color = '#5A77DF'
    },
		beforeRouteLeave(to, from, next) {
        document.body.style.backgroundColor = '#FFFFFF';
        document.body.style.color = '#5A77DF';
        next();
    },
}
import '@/assets/js/home.js'
</script>
<style scoped>@import '@/assets/css/home.css';</style>