package com.wsb.findapart.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur luctus tincidunt lacus, in consectetur nunc porttitor eu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec urna justo, fringilla at commodo nec, volutpat tempus justo. Duis varius ipsum felis, sed faucibus libero pharetra a. Aliquam sagittis, nisi vel feugiat condimentum, metus augue convallis orci, non eleifend tortor velit vitae urna. Donec egestas mattis justo ut placerat. Vestibulum a aliquet ligula. Cras sollicitudin risus eget magna lobortis, non scelerisque mauris interdum. Vestibulum sit amet dolor rutrum, sodales diam at, maximus nibh.\n" +
                "\n" +
                "Quisque dictum mauris vel mauris auctor facilisis et id justo. Nullam turpis velit, consequat at massa vehicula, tincidunt lobortis sem. Donec eu quam turpis. Aenean sed massa eu velit luctus mattis nec et odio. Sed egestas augue eget purus consectetur porttitor. Praesent lacinia vitae felis at efficitur. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In hac habitasse platea dictumst. Etiam vulputate dignissim sem, eget maximus est molestie vitae. Curabitur tincidunt libero sit amet tellus malesuada vestibulum. Pellentesque ornare diam non vestibulum ornare. Suspendisse id mattis massa, in congue erat. Aliquam erat volutpat.\n" +
                "\n" +
                "Vivamus euismod, erat eget vehicula rutrum, nisi lacus egestas lectus, placerat ornare risus enim eget arcu. Ut aliquam, orci scelerisque fermentum tristique, quam ipsum accumsan est, sit amet auctor metus purus a urna. Curabitur vel metus a leo accumsan auctor non non magna. Morbi condimentum ipsum vel porta posuere. Phasellus semper laoreet sodales. Fusce eu commodo velit. Vestibulum condimentum tempus turpis eget condimentum. Duis rutrum sem nec dignissim porta.\n" +
                "\n" +
                "Integer dictum venenatis consectetur. Aenean aliquet lectus ut magna condimentum, in iaculis augue sodales. Etiam dictum semper urna, id ultrices tortor porta hendrerit. Nulla facilisi. Suspendisse eget egestas turpis. Praesent ac cursus lacus. Morbi fermentum lacinia nibh eget interdum. Praesent congue luctus interdum. Aliquam sit amet laoreet risus. Duis auctor lobortis iaculis. Praesent id bibendum est. Integer in dolor consectetur, dictum erat ac, iaculis ex. Mauris in eros ante. Vestibulum consectetur pretium tortor quis vehicula.\n" +
                "\n" +
                "Integer in bibendum elit. Vestibulum consectetur rutrum mauris non gravida. In sit amet urna tristique, congue libero quis, facilisis mi. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras elit quam, pulvinar ut odio ut, aliquam pretium nunc. Etiam viverra tellus sed nulla vehicula, sit amet dapibus ligula interdum. Etiam facilisis, risus a feugiat sodales, arcu nisl mollis odio, vel venenatis eros risus aliquet velit. Ut nec dui vel leo euismod porttitor. Integer non vehicula neque."
    }
    val text: LiveData<String> = _text
}