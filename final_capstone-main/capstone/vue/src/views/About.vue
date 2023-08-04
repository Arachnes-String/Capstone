<template>
  <div class="about">
      <div class="person" v-for="person in people" :key="person.name">
          <img :src="require(`../assets/image/${person.image}`)" :alt="person.name" class="profile-picture">
          <div class="person-info">
          <h2>{{ person.name }}</h2>
         
          <img :src="require(`../assets/blurbImage/${person.blurbImage}`)" :alt="person.name" class="blurb-image">
          </div>
      </div>
  </div>
</template>

<script>


export default {
    data() {
        return {
            people: [
                {
                    image: 'HoiPic.jpg',
                    name: 'Hoi Chen',
                    blurbImage: 'HoiAbout.jpg',

                },

                {
                    image: 'DemPic.jpg',
                    name: 'Demetrio Gutierrez Finley',
                    blurbImage: 'DemAbout.jpg',

                },

                 {
                    image: 'DeloraPic.jpg',
                    name: 'Delora Howell',
                    blurbImage: 'DeloraAbout.jpg',

                },
                
                {
                    image: 'AlexPic.jpg',
                    name: 'Alex H Stone',
                    blurbImage: 'AlexAbout.jpg',

                }
            ]
        }
    }

}
</script>

<style>

</style>